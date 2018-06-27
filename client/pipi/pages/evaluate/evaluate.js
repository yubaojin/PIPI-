var app = getApp();
Page({
  data: {
    //1是匿名 2是不匿名
    isAnonymous: 1,
    //评价商品列表
    disks: [],
    //评加列表
    evals: [],

    tempFiles: [],

  },
  onLoad(options) {
    var _this = this
    console.log("评论加载时F传来的数据", options)
    wx.request({
      url: app.getHeader() + '/order/getDisksByOrderId',
      data: {
        orderId: options.orderId
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data, "初始化订单***********")
        if (res.data.status == 200) {
          _this.setData({
            disks: res.data.data
          })
          var evals = [];
          for (var i = 0; i < _this.data.disks.length; i++) {
            var evalu = {}
            evalu.diskId = _this.data.disks[i].diskId
            evalu.orderId = _this.data.disks[i].orderId
            evalu.openid = app.globalData.openid
            evalu.evalValue = 3
            evalu.imgs = ["",]
            evals[i] = evalu
          }
          _this.setData({
            evals: evals
          })
        } else {
          app.showToast(res.data.msg)
        }
      },
      fail() {
        app.showToast("订单列表请求失败!")
      }
    })
  },
  choosePic: function (e) {
    console.log(e)
    var _this = this
    var evalsIndex = e.currentTarget.dataset.eavlsindex
    var imgsIndex = e.currentTarget.dataset.imgsindex
    wx.chooseImage({
      count: 3 - imgsIndex, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        console.log(res, "选着照片成功")
        var tempFilePaths = res.tempFilePaths
        var tempFiles = _this.data.tempFiles
        console.log(tempFiles)
        for (var i = 0; i < tempFilePaths.length; i++) {
          wx.uploadFile({
            url: app.getHeader() + '/pic/upload', //真实的接口地址
            filePath: tempFilePaths[i],
            name: 'uploadFile',
            temp: tempFiles,
            formData: {
            },
            success: function (res) {
              console.log("图片上传到自己oss上返回数据", res)
              //do something
              var data = JSON.parse(res.data);
              tempFiles.push(data.url)
              var evals = _this.data.evals
              for (var i = imgsIndex, j = 0; i < 3, j < tempFiles.length; i++ , j++) {
                evals[evalsIndex].imgs[i] = tempFiles[j]
              }

              if (evals[evalsIndex].imgs.length < 3) {
                evals[evalsIndex].imgs[evals[evalsIndex].imgs.length] = "";
              }
              _this.setData({
                tempFiles: [],
                evals: evals
              })
            }
          })
        }
      }
    })

  },
  /**
   * 评价提交
   */
  toSubmit: function () {
    var _this = this
    wx.showModal({
      title: '提示',
      content: '确认提交吗',
      success: function (res) {
        if (res.confirm) {
          /**
          * 数据处理一边 主要是图片 图片这里是数组 后台只是一个用,分割了的字符串
          */
          var evals = _this.data.evals
          var isAnonymous = _this.data.isAnonymous
          for (var i = 0; i < evals.length; i++) {
            if (evals[i].imgs[evals[i].imgs.length - 1] == "") {
              evals[i].imgs.pop()
            }
            evals[i].imgs = evals[i].imgs.join(",")
          }
          /**
           * 处理匿名和不匿名问题
           */

          /**
           * 提交评论
           */
          wx.request({
            url: app.getHeader() + '/evaluate/saveEvals',
            data: {
              isAnonymous: isAnonymous,
              evals: evals,
              nickname: app.globalData.userInfo.nickName,
              avatarUrl: app.globalData.userInfo.avatarUrl
            },
            header: {
              'content-type': 'application/json' // 默认值
            },
            success: function (res) {
              console.log(res.data, "提交评论后请求返回的数据")
              if (res.data.status == 200) {
                wx.navigateBack({
                  delta: 1
                })
              } else {
                app.showToast(res.data.msg)
              }
            },
            fail() {
              app.showToast()
            }
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },
  del: function (e) {
    console.log(e, "删除图片")
    var _this = this;
    var evalsIndex = e.currentTarget.dataset.eavlsindex
    var imgsIndex = e.currentTarget.dataset.imgsindex
    var evals = _this.data.evals

    if (evals[evalsIndex].imgs.length == 3 && imgsIndex == 2) {
      evals[evalsIndex].imgs[imgsIndex] = ""
    } else {
      /**
    * 把后一个覆盖要删除的
    */
      evals[evalsIndex].imgs[imgsIndex] = evals[evalsIndex].imgs[imgsIndex + 1];
      /**
    * 最后一个出栈
    */
      evals[evalsIndex].imgs.pop()
    }


    /**
     * 植入数据
     */
    _this.setData({
      evals: evals
    })
  },
  changeText(e) {
    var _this = this
    console.log("文本框失去焦点时", e, _this.data.evals)
    var index = e.target.dataset.index;
    var evals = _this.data.evals;
    evals[index].content = e.detail.value
    _this.setData({
      evals: evals
    })
  },
  //差中好评但按钮事件
  radioChange: function (e) {
    var _this = this
    console.log('radio发生change事件，携带value值为：', e, _this.data.evals)
    var index = e.target.dataset.index;
    var evals = _this.data.evals;
    evals[index].evalValue = e.detail.value
    _this.setData({
      evals: evals
    })
  },
  //匿名选着
  changeAnonymous: function () {
    this.setData({
      isAnonymous: (this.data.isAnonymous + 1) % 2
    })
  },
})