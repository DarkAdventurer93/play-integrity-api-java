# play-integrity-api-java
<p>
Play Integrity Api的后端代码示例，见Main.java
</p>
<p>
起因是谷歌要求从SafetyNet API迁移至Play Integrity API
</p>
<p>
Android端代码见https://github.com/HairySnow/play-integrity-api-android
</p>
<p>
需要app端传递的参数有（已在Android端进行封装，见上面地址的PlayIntegrityHelper类）：

</p>
<p>
String integrityToken = "<your integrity token from app>" //app端调用Play Integrity API获取到的integrityToken
</p>
<p>
String applicationName = "<your application name>" //应用名
</p>
<p>
String packageName = "<your packageName>" //包名
</p>
<p>
String credentialJsonPath = "<your credential json from google cloud platform>";//从谷歌控制台获取到的授权JSON文件
</p>
在Google Cloud 中选中自己创建的特定的项目如图的(没有需要自己创建，图中是Android-App)
然后到Service Accounts下，点击右边的ADD KEY按钮创建新key，完成之后会有一份JSON文件
![image](https://user-images.githubusercontent.com/24764220/203706365-1f542a43-a0c8-4b6b-82b2-6fda5ae8e414.png)
