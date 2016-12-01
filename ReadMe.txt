注意事项：
1.右键项目名称，选择Preferences-Libraries,双击JRE System Library，选择Alternate JRE,选择jdk1.8（不要选择jre）
2.在Libraries选项卡右侧，点击Add External JARs...，找到本机MySQL安装位置，选择mysql-connector-java-xxx-bin.jar,完成后点击OK
3.运行程序：选择Run As-Java Application,选择com.jfinal.core.运行开始后，在浏览器中输入localhost/test,出现JSP Test
4.5.如果某些文件出现乱码，设置eclipse的默认字符集为UTF-8。具体如下：Window-Preferences-General-Workspace-左下角选择Other-Utf-8