1. 在项目的 local.properties 文件中（就是最后一处红线的位置），添加我们第二步注册网站是拿到的两个参数。
    
    ```    
    bintray.user = username
    bintray.apikey =api key
    ```
2. 一切就绪之后，在根目录的控制台执行

    `gradle build`

    `gradle bintrayUpload`

    就可以上传了我们的 common

3. 然后登陆 bintray.com 就可以看到我们项目了，然后在项目里点击 add to Jcenter 按钮就可以发布到 Jcenter . 发布成功会有邮件通知你。