<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${absoluteContextPath}/js/util/jquery.min.js"></script>
    <script type="text/javascript" src="${absoluteContextPath}/js/util/spark-md5.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<input id="lefile" type="file" style="display:none">
<input id="fileCover" class="input-large" type="text" value="" style="height:30px;" readonly="readonly">
<a class="btn" onclick="$('input[id=lefile]').click();">选择</a>
<span id="complete"></span>

<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
<button type="button" class="btn btn-primary" id="import" onclick="uploadLoading()" data-loading-text="正在导入">导入</button>
<script type="text/javascript">
    var
        $uploadProduceModal;
    // 每个文件切片大小定为5M--该值需要和后台一致
    var BYTES_PER_SLICE = 5*1024*1024;
    // 总切片数
    var totalSlices;
    $(function () {
        $uploadProduceModal = $('#uploadProduceModal');

        $uploadProduceModal.on('show.bs.modal', function () {
            $('#uploadProduceModal').find('.modal-body').show();
        });
        $uploadProduceModal.on('hidden.bs.modal', function () {
            $('#uploadProduceModal').find('.modal-body').hide();
        });
        //选择升级包
        $('input[id=lefile]').change(function() {
            $('#fileCover').val($(this).val());
        });

    });

    function uploadLoading(){
        $('#import').button('loading');
        setTimeout(function(){uploadProduce()},100)
    }
    //导入升级包
    function uploadProduce() {
        var type="";
        var flag=1;//该分片是否上传
        var files = $('#lefile').prop('files');
        var description = $('#description').val();
        var file = files[0];
        // 当前片数
        var index = 0;
        // 分片的开始、结束（不含）
        var start,end;
        // 文件名
        var fileName = file.name;
        //获取文件md5
        countMD5(file).then(function(result){
            type = checkPack(fileName,result);
            if(type==1){//1 name同 md5同 2. name不同 md5同 3 name同 md5不同 4 name不同 md5不同
                alert("存在相同的升级包,无法导入");
                $('#description').removeAttr('readonly');
                $('#import').button('reset');
                $("#complete").html("")
                return;
            }
            if (type==2){
                $("#complete").html("")
                return;
            }
            if(type==3){
                if(!confirm("存在同名的升级包，是否覆盖")){
                    $('#description').removeAttr('readonly');
                    $('#import').button('reset');
                    $("#complete").html("");
                    return;
                }
            }
            $("#complete").html("1%");
            // 计算文件切片总数（向上取整）
            totalSlices = Math.ceil(file.size / BYTES_PER_SLICE);
            var md5Array = [];
            while(index < totalSlices){
                start = index*BYTES_PER_SLICE;
                end = start + BYTES_PER_SLICE;
                var slice =file.slice(start,end);//切割文件
                md5Array.push(countMD5(slice))
                index++;
            }
            //须将同步体包围在里面或者又会是异步操作
            Promise.all(md5Array).then(function(result) {//将分片md5全部算出
                index = 0; //重新归0 做后续操作
                // 不断循环将切片上传
                var uploadPromise = [];
                while(index < totalSlices) {
                    start = index*BYTES_PER_SLICE;
                    end = start + BYTES_PER_SLICE;
                    var slice =file.slice(start,end);//切割文件
                    //promise 将异步请求转化为同步请求
                    //断点上传：将分片和后台存在的分片做比较如果存在该分片就跳过该分片即可达到断点上传的目的
                    //获取分片md5
                    if(flag != 2) {//该值为2时代表这个包是全新上传，所有不需要再进行校验
                        flag = checkSlice(result[index], fileName, index, totalSlices);
                    }
                    if (flag ==1 || flag ==2) {
                        uploadPromise.push(uploadPar(slice, index, fileName,totalSlices));
                    }
                    index++;
                }
                //全部分片上传完后再进行合并
                Promise.all(uploadPromise).then(function (result) {
                    console.log("正在合并");
                    var combineFile = {"fileName":fileName,"description":description,"total":totalSlices}
                    $.ajax({
                        async: false,
                        url: "${absoluteContextPath}/combineFile",
                        type: 'POST',
                        data: combineFile,
                        dataType: 'json',
                        success:function () {
                            $("#complete").html("100%");
                            //停0.5秒刷新一下页面
                            setTimeout(function() { location.reload() }, 500);
                        }
                    })
                })
            })
        });
    }
    //分片上传
    function uploadPar(slice,index,fileName,total){
        var p = new Promise(function (resolve, reject) {
            var data = new FormData();
            //上传分片
            data.append('fileUpload', slice);
            data.append('fileName', fileName);
            data.append('index', index);
            $.ajax({
                async: true,//异步上传
                url: "${absoluteContextPath}/uploadProduce",
                type: 'POST',
                data: data,
                cache: false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType: false,//默认为true,要求为string类型的参数
                // 告诉jQuery不要去处理发送的数据，用于对data参数进行序列化处理 这里必须false
                processData: false,
                success: function (data) {
                    $("#complete").html(Math.ceil(parseInt(data) / total * 100 -1) + "%");
                    resolve();
                }
            });
        });
        return p;
    }
    //用于断点上传判断该分片后台是否已经存在
    function checkSlice(md5,fileName,index,total){
        var flag ;
        var data = {"md5":md5,"fileName":fileName,"index":index,"total":total};
        $.ajax({
            async: false,//必须为同步需要通过才能进行上传操作
            url: "${absoluteContextPath}/checkSlice",
            type: 'post',
            dataType: 'json',
            data:data,
            success: function (data) {
                flag = data;
            }
        });
        return flag;
    }
    //用于文件计算md5值
    function countMD5(file) {    //这里假设直接将文件选择框的dom引用传入
        var p = new Promise(function (resolve, reject) {
            //这里需要用到File的slice( )方法，以下是兼容写法
            var blobSlice = File.prototype.slice || File.prototype.mozSlice || File.prototype.webkitSlice,
                chunkSize = 5*1024*1024,                           // 以每片2MB大小来逐次读取
                chunks = Math.ceil(file.size / chunkSize),
                currentChunk = 0,
                spark = new SparkMD5(), //创建SparkMD5的实例
                //Promise 可以获取异步请求
                fileReader = new FileReader();
            fileReader.onload = function (e) {
                spark.appendBinary(e.target.result);
                currentChunk += 1;
                if (currentChunk < chunks) {
                    loadNext();
                } else {
                    resolve(spark.end());     // 完成计算，返回结果
                }
            };
            loadNext();
            function loadNext() {
                var start = currentChunk * chunkSize,
                    end = start + chunkSize >= file.size ? file.size : start + chunkSize;
                fileReader.readAsBinaryString(blobSlice.call(file, start, end));
            }
        });
        return p;
    }
    //是否进行升级判断
    function checkPack(fileName,fileMd5){
        var data = new FormData();
        var type ="";
        var name ="";
        data.append("fileName",fileName)
        data.append("fileMd5",fileMd5);
        $.ajax({
            async: false,
            url: "${absoluteContextPath}/checkPack",
            type: 'post',
            data: data,
            cache:false,
            contentType: false,//默认为true,要求为string类型的参数
            processData: false,//默认为true时:将数据转化为对象
            success:function (data) {
                type = data.type;
                name = data.name;
            },
            error:function () {
                $('#import').button('reset');
                alert("后台校验升级包失败");
            }
        })
        if (type==2){
            alert("存在内容相同且名为"+name+"的升级包,请删除该包后上传");
        }
        return type;
    }
 </script>
</body>
</html>