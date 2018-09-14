/**
 *  login.js
 *  功能       添加账户视图
 *  作者       xxx
 *  日期       2018-08-xx
 *  当前路由 /login
 *  前一路由 /
 *  后一路由 /agreement
 *  输出变量 window.app.loginV
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

    // 构造视图DOM模板
    var loginDomTemplate = '<div class="header-bar">\
    <a class="prev-btn" data-bind="click: prevPage"><i class="fa fa-chevron-left"></i></a>\
    <span class="header-title">登&nbsp;&nbsp;录</span>\
    </div>\
    \
    <div class="container-fluid" style="margin-top:15%;">\
  \
    <form class="form-horizontal form-login">\
  \
      <div class="form-group">\
        <div class="col-xs-12"><br></div>\
      </div>\
      <div class="form-group">\
      <div class="col-xs-3"><label>账&nbsp;&nbsp;&nbsp;号</label></div>\
      <div class="col-xs-8">\
        <input type="text" class="form-control" name="idCard"\
          placeholder="请输入掌银账号"\
          data-bind="value:userId" required validationMessage="请输入正确格式的掌银账号!">\
      </div>\
      </div>\
    \
      <div class="form-group">\
      <div class="col-xs-3"><label>密&nbsp;&nbsp;&nbsp;码</label></div>\
      <div class="col-xs-8">\
        <input type="password" class="form-control" name="password"\
          placeholder="请输入密码"\
          data-bind="value: password" required validationMessage="请输入密码!">\
      </div>\
      </div>\
      <div class="form-group">\
        <div class="col-xs-12"><br></div>\
      </div>\
  </form>\
  \
  <br/><br/><br/>\
    <div class="login-bar">\
      <button class="btn btn-theme btn-login" data-bind="click: login"><b>登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</b></button>\
    </div>\
  \
  </div>';

  // 使用template构造DOM字符串，传入从上一视图读取的变量
  var loginDomString = kendo.template(loginDomTemplate) ({

  });

  // login视图view modal
  var loginVM = kendo.observable({
    appId:'uan',
    userId:'110110200001011234', // TODO - 示例数据，待清除
    password: '000000000', // TODO - 示例数据，待清除
    init: function () {
    },
    // show: function () {
    //   console.log('view show', this.customerName);
    // },
    // hide: function () {
    //   console.log('view hide', this.customerName);
    // },
    prevPage: function() {
      router.navigate('/');
    },
    login: function () {
      var config = window.app.proxy.login;
      udesk.ajax({
        type: config.type,
        url: config.url, // TODO - 待修改为后端登录URL
        contentType: config.contentType,
        data: JSON.stringify({
          appId: this.appId,
          userId: this.userId,
          data:this.userId
//          password: this.password
        }),
      })
      .done(function(res) {
        // 登录返回成功，将返回的用户数据写入window.app.loginV.model
        if(res.statusCode == '200') {
          var data = JSON.parse(res.data);
          $.extend(true, window.app.loginV.model, {
            id: data.id,
            idCard: data.idCard,
            name: data.name,
            phone: data.phone,
            state: data.state,
            type: data.type,
          });
          // 跳转路由至 /agreement
          router.navigate('/agreement');
        }
        
      })
      .fail(function(jqXHR, textStatus, errorThrown) {
        // TODO - 登录返回失败

      }); // END OF ajax

    }, // END OF login
  });

  // login视图view，从字符串变量loginDomString中加载DOM结构，绑定ViewModel
  var loginV = new kendo.View(loginDomString, {
    model: loginVM,
    // init: loginVM.init.bind(loginVM),
    // show: loginVM.show.bind(loginVM),
    // hide: loginVM.hide.bind(loginVM),
    login: loginVM.login.bind(loginVM),
    prevPage: loginVM.prevPage.bind(loginVM)
  });

  // agreement视图路由，渲染指定view
  router.route('/login', function () {
    layout.showIn(layoutSelector, loginV);
  });


  // 输出至全局变量app
  window.app = $.extend(true, app, {
    'loginV': loginV
  });




}));