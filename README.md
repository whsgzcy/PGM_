# 前言
1、su.txt是我从Android设备中抓取出来的，当然在任何平台pushlish /map 也是可以获取的

2、这个要解决的是把pgm文件代码转换成bmp文件，然后ps编辑此文件后，再把编辑后的bmp文件，通过color的标记-1、1等写成800*800的矩阵，最后稍微处理一下，写成优化后的pgm文件，这样给客户的体验要好很多

3、其实代码是没有难度的，只是觉得通过color标记，这个解决问题的思路还是比较新颖的，也很容易联想到一个被涂鸦的图片如何恢复，怎么尽可能的读出来，这还是很有意思的


## 1、图片
一定得是bmp，不能是其他格式:

![MacDown Screenshot](https://github.com/whsgzcy/PGM_/blob/master/map1.png)

