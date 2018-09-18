/**
 *  faceidlogin.js
 *  功能       刷脸登录视图
 *  作者       LIU Jiajie
 *  日期       2018-09-xx
 *  当前路由 /faceidlogin
 *  前一路由 /
 *  后一路由 /uanindex
 *  输出变量  window.app.faceidLoginV
 * 
 * Copyright Agriculture Bank of China.
 */

;(function(factory) {
    if (typeof define === 'function' && define.amd) {
      // AMD. Register as anonymous module.
      define(['jquery'], function(jQuery) {
        return factory(jQuery, window, document);
      });
    } else if (typeof module === 'object' && module.exports) {
      // Node. Does not work with strict CommonJS, but
      // only CommonJS-like enviroments that support module.exports,
      // like Node.
      module.exports = factory(require('jquery'), window, document);
    } else {
      // Browser globals.
      factory(jQuery, window, document);
    }
  }(function($, window, document, undefined) {
    'use strict';
  
  // 获取全局变量，若为空则对其初始化
  var kendo = window.kendo,
    app = window.app,
    layout = app.layout,
    router = app.router,
    layoutSelector = app.layoutSelector;
  
    // // 构造视图DOM模板
    // var faceidLoginDomTemplate = '<a class="atm-btn btn-right btn-bg btn-row-4" data-bind="click: faceidLogin">模拟刷脸</a>\
    // <a class="atm-btn btn-left btn-bg btn-row-4" data-bind="click: prevPage">退&nbsp;&nbsp;出</a>\
    // <div class="container-fluid">\
    //   <h1 style="margin: 21px auto; width: 170px;">刷脸登录</h1>\
    //   <div style="width: 500px; margin: 20px auto;">\
    //     <img src="image/face-scanner.jpg" />\
    //   </div>\
    //   <div style="width: 660px; margin: 20px auto;">\
    //     <span style="font-size: 26px;">\
    //       1. 请正视摄像头 <br>\
    //       2. 请露出额头，免冠，面部无遮挡（切勿佩戴眼镜） <br>\
    //       3. 请与摄像头保持50cm距离 <br>\
    //     </span>\
    //     <br/>\
    //   </div>\
    // </div>';
  
  // 构造视图DOM模板
  var faceidLoginDomTemplate = '<a class="atm-btn btn-right btn-row-3" data-bind="click: faceidLogin">确认/OK</a>\
  <a class="atm-btn btn-right btn-row-4" data-bind="click: prevPage">取消/Cancle</a>\
  <div class="container-fluid" style="padding: 40px 50px 50px 50px;">\
\
    <div class="faceid-instruction-img">\
      <div class="step-1"><img src="image/faceid-login-1.png" /></div>\
      <div class="step-2"><img src="image/faceid-login-2.png" /></div>\
      <div class="step-3"><img src="image/faceid-login-3.png" /></div>\
    </div>\
\
    <div class="faceid-instruction-text">\
      <div class="step-1">\
        <div class="zh-CN">1. 请正视摄像头，然后正脸向右转90度。</div>\
        <div class="en-US">1.Please look directly at the camera, then turn your face to the RIGHT about 90 degrees.</div>\
      </div>\
      <div class="step-2">\
        <div class="zh-CN">2. 请回正。</div>\
        <div class="en-US">2. Please turn your face back to the original direction.</div>\
      </div>\
      <div class="step-3">\
        <div class="zh-CN">3. 请正视摄像头。</div>\
        <div class="en-US">3. Please look directly at the camera.</div>\
      </div>\
    </div>\
  </div>';

    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var faceidLoginDomString = kendo.template(faceidLoginDomTemplate) ({});
  
  
    // faceidLogin视图view modal
    var faceidLoginVM = kendo.observable({
      // request
      appId: 'uan',
      idCard: '',
      faceBytes: 'base64string',
      userId: '',
      // respond
      id: '',
      name: '',
      phone: '',
      state: '',
      type: '',
      init: function () {
      },
      // show: function () {
      //   console.log('view show', this.customerName);
      // },
      // hide: function () {
      //   console.log('view hide', this.customerName);
      // },
      prevPage: function(e) {
        // 跳转路由至 /
        router.navigate('/');
      },
      faceidLogin: function(e) {
        // var _this = this;
        // var config = window.app.proxy.faceidLogin;
        // 使用ajax提交表单
        // udesk.ajax({
        //   type: config.type,
        //   url: config.url, // TODO- 待修改为后端登录URL
        //   contentType: config.contentType,
        //   data: JSON.stringify({
        //     appId: _this.appId,
        //     data: {
        //       idCard: _this.idCard,
        //       faceBytes: _this.faceBytes,
        //     },
        //     userId: _this.userId
        //   }),
        // })
        // .done(function(res) {
        //   // console.log(res);
        //   // 登录返回成功，将返回的用户数据写入window.app.faceidLogin.model
        //   if(res.statusCode == '200') {
        //     var data = res.data, model = window.app.faceidLoginV.model;
        //     model.set('id', data.id);
        //     model.set('idCard', data.idCard);
        //     model.set('name', data.name);
        //     model.set('phone', data.phone);
        //     model.set('state', data.state);
        //     model.set('type', data.type);
        //     // 跳转路由至 /uanindex
        //     router.navigate('/uanindex');
        //   }
          
        // })
        // .fail(function(jqXHR, textStatus, errorThrown) {
        //   // TODO - 登录返回失败
  
        // }); // END OF ajax

        // 跳转路由至 /idcardlogin
        router.navigate('/idcardlogin');
      },
    });
  
    // faceidLogin视图view，从字符串变量faceidLoginDomString中加载DOM结构，绑定ViewModel
    var faceidLoginV = new kendo.View(faceidLoginDomString, {
      model: faceidLoginVM,
      // init: faceidLoginVM.init.bind(faceidLoginVM),
      // show: faceidLoginVM.show.bind(faceidLoginVM),
      // hide: faceidLoginVM.hide.bind(faceidLoginVM),
      faceidLogin: faceidLoginVM.faceidLogin.bind(faceidLoginVM),
    });
  
    // faceidLogin视图路由，渲染指定view
    router.route('/faceidlogin', function () {
      layout.showIn(layoutSelector, faceidLoginV);
    });
  
    // 输出至全局变量app
    window.app = $.extend(true, app, {
      'faceidLoginV': faceidLoginV
    });
  }));