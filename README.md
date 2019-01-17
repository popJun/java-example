# java-example
对java-api，maven等常用工具的使用例子，或者一些功能的实现例子

### init_test
- maven-anturn-plugin的使用，主要可以用于项目初建时对数据库进行初始化。
- 使用方法：建立对应的数据库，双击db-init.bat 进行初始化

### upload-example-demo
- 完成分片上传和断点上传
- 前端使用到spark-md5.js（计算md5）、slice（分割文件）、Promise（类似于多线程中的callable，可以等待异步操作全部执行完后返回结果在执行后面操作）
- 后端主要使用到RandomAccessFile来合并
- springboot还需要配置文件上传大小限制
  ```
  spring.servlet.multipart.max-file-size=100MB
  spring.servlet.multipart.max-request-size=1000MB
  ```
