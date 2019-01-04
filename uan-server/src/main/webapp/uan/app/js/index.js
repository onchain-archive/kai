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
 *  index.js
 *  功能       首页视图
 *  作者       LIU Jiajie
 *  日期       2018-09-xx
 *  当前路由 /
 *  前一路由 无
 *  后一路由 /login
 *  输出变量  window.app, indexV
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
  var kendo = window.kendo;
  
  // 构造布局DOM模板
  var layoutDomTemplate = '<section id="content"></section>',
    layoutSelector = '#content',
    rootSelector = '#app';
  
  // 使用template构造DOM字符串，传入从上一视图读取的变量
  var layoutDomString = kendo.template(layoutDomTemplate)({});

  var rt = new kendo.Router({
    routeMissing: function(e) {
      console.error('路由"', e.url, '"不存在，跳转至默认路由 "/" ...');
      e.sender.navigate('/'); // 路由错误
      // 路由重定向至 /
      window.app.redirectTo();
    }
  });
  rt.bind('init', function () {
    layout.render($(rootSelector));
  });

  // 输出至全局变量app
  window.app = $.extend(true, window.app || {}, {
    layout: new kendo.Layout(layoutDomString, {wrap:false}),
    layoutSelector: layoutSelector,
    router: rt,
    proxy: {
    	login : {
			// 对应login.js，发送登录请求，返回签约合同
			type : 'POST',
			url : getPath()+'api/rest/cusinf/confirmCustomer', // TODO -
			// 待修改为后端登录URL
			contentType : 'application/json',
		},
		addaccount : {
			// 对应addaccount.js，发送本行账户请求，返回账户信息
			type : 'POST',
			url : getPath()+'api/rest/bancar/findMyCard', // TODO -
			// 待修改为后端findMyCard
			// URL
			contentType : 'application/json',
		},
		checkinfo : {
			// 对应checkinfo.js，发送签约账户和互助人信息，返回签约结果
			type : 'POST',
			url : getPath()+'api/rest/uancon/contract', // TODO- 待修改为后端登录URL
			contentType : 'application/json',
		}
    },
    redirectTo: function(path) {
      window.location.href = window.location.href.split('#')[0] + '#' + (path || '/');
    }
  });



  // 构造index页面
  var app = window.app,
    layout = app.layout,
    router = app.router,
    layoutSelector = app.layoutSelector;

  //   <tr>\
  //   <td><div class="service"><img class="icon" src="image/fund.png"/><div class="name">Fund</div></div></td>\
  //   <td><div class="service"><img class="icon" src="image/financial.png"/><div class="name">Financial</div></div></td>\
  //   <td><div class="service"><img class="icon" src="image/metal.png"/><div class="name">Heavy Metal</div></div></td>\
  //   <td><div class="service"><img class="icon" src="image/lifePay.png"/><div class="name">Utilities</div></div></td>\
  // </tr>\


  // 构造视图DOM模板
  var indexDomTemplate = '<header>\
    <div class="qr-scan" ><img src="image/flicking.png" /></div>\
    <div class="header-search">\
      <div class="left-icon"><label class="fa fa-search" for="search-input"></label></div>\
      <input id="search-input" name="keywords" placeholder="Quick Pass"/>\
      <span class="right-icon"><label class="fa fa-microphone"></label></span>\
    </div>\
    <div class="message"><img src="image/mail.png" /></div>\
    <div class="customer-service mr-30"><img src="image/customerService.png" /></div>\
  </header>\
  <div class="container-fluid index">\
    <div class="ad"></div>\
\
  <div class="main">\
    <div class="greetings">Welcome!</div>\
    <table width="100%"><tbody>\
      <tr>\
        <td width="25%"><div class="service"><img class="icon" src="image/account.png"/><div class="name">My Accounts</div></div></td>\
        <td width="25%"><div class="service"><img class="icon" src="image/transfer.png"/><div class="name">Transfer</div></div></td>\
        <td width="25%"><div class="service"><img class="icon" src="image/e.png"/><div class="name">Quick Pay</div></div></td>\
        <td width="25%"><div class="service"><img class="icon" src="image/eCredit.png"/><div class="name">Quick Loan</div></div></td>\
      </tr>\
      <tr>\
        <td><div class="service"  data-bind="click: sign"><img class="icon" src="image/uan.png"/ width="64px" height="64px"><div class="name">Emergency Service Contract</div></div></td>\
        <td><div class="service"><img class="icon" src="image/all.png"/><div class="name">More</div></div></td>\
        <td><div class="service"><img class="icon"/><div class="name"></div></div></td>\
        <td><div class="service"><img class="icon"/><div class="name"></div></div></td>\
      </tr>\
      </tbody></table>\
  </div>\
\
  </div>\
  <footer><table width="100%"><tbody>\
    <tr>\
      <td width="20%"><div class="service"><img class="icon" src="image/index_green.png"/><div class="name">Home</div></div></td>\
      <td width="20%"><div class="service"><img class="icon" src="image/investment.png"/><div class="name">Investment</div></div></td>\
      <td width="20%"><div class="service"><img class="icon" src="image/creditCard.png"/><div class="name">Credit Card</div></div></td>\
      <td width="20%"><div class="service"><img class="icon" src="image/life.png"/><div class="name">Life</div></div></td>\
      <td width="20%"><div class="service"><img class="icon" src="image/mine.png"/><div class="name">Me</div></div></td>\
    </tr>\
  </tbody></table></footer>';

{/* <div class="secretary">\
  <div class="">金融小秘书</div>\
</div>\
\
<div class="quick-e-loan">\
  <div class="">农行快E贷</div>\
</div>\ */}


  // 使用template构造DOM字符串，传入从上一视图读取的变量
  var indexDomString = kendo.template(indexDomTemplate) ({

  });


  // index视图view modal
  var indexVM = kendo.observable({
    init: function () {
      if(jQuery.fn.niceScroll) {
        $('.container-fluid').last().niceScroll({cursorcolor:'#7f7f7f'});
      }
    },
    // show: function () {
    //   console.log('view show', this.customerName);
    // },
    // hide: function () {
    //   console.log('view hide', this.customerName);
    // },
    sign: function() {
      // 跳转路由至 /login
      router.navigate('/login');
    }
  });

  // index视图view，从字符串变量indexDomString中加载DOM结构，绑定ViewModel
  var indexV = new kendo.View(indexDomString, {
    wrap: false,
    model: indexVM,
    init: indexVM.init.bind(indexVM),
    // show: indexVM.show.bind(indexVM),
    // hide: indexVM.hide.bind(indexVM),
    sign: indexVM.sign.bind(indexVM),
  });

  // agreement视图路由，渲染指定view
  router.route('/', function () {
    layout.showIn(layoutSelector, indexV, 'slide');
  });

  // 输出至全局变量app
  window.app = $.extend(true, app, {
    'indexV': indexV
  });
}));