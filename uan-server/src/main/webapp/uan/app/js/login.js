/*
 * Copyright 2018 Liu Jiajie
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 * 
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
    var loginDomTemplate = '<header>\
      <div class="prev-btn" data-bind="click: prevPage"><i class="fa fa-chevron-left"></i></div>\
      <div class="header-title">Login</div>\
    </header>\
    \
    <div class="container-fluid">\
    <form class="form-horizontal form-login">\
  \
      <div class="form-group">\
        <div class="col-xs-12">\
          <div class="avatar"><img src="image/avatar.png" /></div>\
        </div>\
      </div>\
      <div class="form-group">\
        <div class="col-xs-12 user-name">\
          <input type="text" class="form-control" name="idCard"\
            placeholder="请输入掌银账号"\
            data-bind="value:userId" required validationMessage="请输入正确格式的掌银账号!">\
        </div>\
      </div>\
    \
      <div class="form-group">\
        <div class="col-xs-12">\
          <div class="fingerprint" data-bind="click: login">\
            <img src="image/fingerprintUnlock.png" />\
            <div class="tip">Fingerprint Unlock</div>\
          </div>\
        </div>\
    </div>\
  </form>\
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
          password: this.password,
          data: this.userId
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
    wrap: false,
    model: loginVM,
    // init: loginVM.init.bind(loginVM),
    // show: loginVM.show.bind(loginVM),
    // hide: loginVM.hide.bind(loginVM),
    login: loginVM.login.bind(loginVM),
    prevPage: loginVM.prevPage.bind(loginVM)
  });

  // agreement视图路由，渲染指定view
  router.route('/login', function () {
    layout.showIn(layoutSelector, loginV, 'slide');
  });


  // 输出至全局变量app
  window.app = $.extend(true, app, {
    'loginV': loginV
  });




}));