/**
 *  agreement.js
 *  功能       签阅条款视图
 *  作者       xxx
 *  日期       2018-08-xx
 *  当前路由 /agreement
 *  前一路由 /login
 *  后一路由 /addaccount
 *  输出变量 window.app.agreementV
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
  var domTemplate = '<header>\
  <div class="prev-btn" data-bind="click: prevPage"><i class="fa fa-chevron-left"></i></div>\
  <div class="header-title">License Agreement</div>\
</header>\
\
  <div class="container-fluid with-bottom with-checkbox"  style="padding-top: 15px;"> \
<div class="article-horizontal"> \
  <p> \
    Welcome to UAN Emergency Service! Your information is as follows:<br> \
<br>\
Full Name：<span data-bind="text: name"></span> <br> \
ID Card No.：<span data-bind="text: idCard"></span> <br> \
Phone：<span data-bind="text: phone"></span><br> \
  \
    <br>为使用UAN服务（简称为：本服务），您应当阅读并遵守《UAN服务协议》（简称为：本协议）。请您务必审慎阅读、充分理解各条款内容，特别是免除或限制责任的相应条款，以及开通或使用某项服务的单独协议，并选择接受或不接受。免除或限制责任条款可能以加粗形式提示您注意。\
除非您已阅读并接受本协议所有条款，否则您无权使用本服务。<br>\
  \ 您对本服务的任何购买或接受赠与等获取行为及登录、查看等任何使用行为即视为您已阅读并同意本协议的约束。\
如果您未满18周岁，请在法定监护人的陪同下阅读本协议，并特别注意未成年人使用条款。<br>\
  \
一、【协议的范围】<br>\
　　1.1【协议适用主体范围】<br>\
　　     本协议是您与中国农业银行UAN应急服务之间关于您使用本服务所订立的协议。<br> \
  \
二、【关于本服务】<br>\
　　 UAN服务，本服务能会根据不同的产品及服务类型，推出不同的包月增值服务，目前，腾讯提供QQ会员、超级会员、黄钻、红钻等不同种类的包月增值服务，同时，腾讯也可能会根据用户的需求、产品及服务类型的变化等，对现有包月增值服务种类进行调整以及不断推出新的包月增值服务种类。腾讯也可能会在不同时间推出具体不同的服务内容，以不断完善、优化本服务。具体包月增值服务种类及服务内容以相关服务页面公布、实际提供的内容为准。您可以自行根据需要选择相应服务。<br>\
　　 您所享有的本服务的具体内容可能会因为级别、是否年费、开通期限、您选择的具体服务类别等因素而有不同，通常高级别、开通年费服务、开通期限较长等情况下，您将会享有更多的服务，具体以相关服务页面公布、实际提供的内容为准。<br>\
  \
</p>\
</div>\
</div>\
<div class="checkbox-bar fix-bottom" style="bottom: 90px">\
<input type="checkbox" name="isAgree" id="isAgreeCheckbox" data-bind="checked: agreeWithUan" autocomplete="off">\
<label for="isAgreeCheckbox" style="float: initial;">I\'ve read through the Licence Agreement above.</label>\
</div>\
<div class="btn-bar fix-bottom">\
<button class="btn btn-default" data-bind="click: prevPage">I don\'t agree.</button>\
<button class="btn btn-theme pull-right" data-bind="click: nextPage,  enabled: agreeWithUan">I agree.</button>\
</div>';

  // 使用template构造DOM字符串，传入从上一视图读取的变量
//  var domString = kendo.template(domTemplate)({
//  });
  var domString = domTemplate;

  // agreement视图view modal
  var agreementVM = kendo.observable({
    // 以下为默认值，待覆盖为loginV.model中的值
    appId: '',
    userId: '',
    password: '',
    id: '',
    idCard: '',
    name: '',
    phone: '',
    state: '',
    type: '',
    agreeWithUan: false,
    init: function () {
    //  var data = window.app.loginV.model;

    //  this.set('appId', data.appId);
    //  this.set('userId', data.userId);
    //  this.set('password', data.password);
    //  this.set('id', data.id);
    //  this.set('idCard', data.idCard);
    //  this.set('name', data.name);
    //  this.set('phone', data.phone);
    //  this.set('state', data.state);
    //  this.set('type', data.type);

    if(jQuery.fn.niceScroll) {
      $('.container-fluid').last().niceScroll({cursorcolor:'#7f7f7f'});
    }

    },
    show: function () {
      this.updateData();
      if(!this.get('name')) {
        console.error('No login information!');
        // route to /login
        app.redirectTo('/login');
        return;
      }
    },
    // hide: function () {
    //   console.log("view hide", this.customerName);
    // },
    updateData: function() {
      var data = window.app.loginV.model;
      this.set('appId', data.appId);
      this.set('userId', data.userId);
      this.set('password', data.password);
      this.set('id', data.id);
      this.set('idCard', data.idCard);
      this.set('name', data.name);
      this.set('phone', data.phone);
      this.set('state', data.state);
      this.set('type', data.type);
    },
    prevPage: function () {
      router.navigate('/login');
    },
    nextPage: function () {
      if(this.agreeWithUan) {
        router.navigate("/addaccount");
      }
    },

  });

  // agreement视图view，从字符串变量domString中加载DOM结构，绑定ViewModel
  var agreementV = new kendo.View(domString, {
    wrap: false,
    model: agreementVM, 
    init: agreementVM.init.bind(agreementVM),
    show: agreementVM.show.bind(agreementVM),
    // hide: agreementVM.hide.bind(agreementVM),
    prevPage: agreementVM.prevPage.bind(agreementVM),
    nextPage: agreementVM.nextPage.bind(agreementVM),
  });

  // agreement视图路由，渲染指定view
  router.route("/agreement", function () {
    layout.showIn(layoutSelector, agreementV, 'swap');
  });


  // 输出视图至全局变量app
  window.app = $.extend(true, app, {
    'agreementV': agreementV
  });
}));
