/**
 * index.js 功能 首页视图 作者 LIU Jiajie 日期 2018-09-xx 当前路由 / 前一路由 无 后一路由 /login 输出变量
 * window.app, indexV
 * 
 * Copyright Agriculture Bank of China.
 */

;
(function(factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD. Register as anonymous module.
		define([ 'jquery' ], function(jQuery) {
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
}
		(function($, window, document, undefined) {
			'use strict';

			// 获取全局变量，若为空则对其初始化
			var kendo = window.kendo;

			// 构造布局DOM模板
			var layoutDomTemplate = '<!-- <header><b>中国农业银行</b></header> -->\
  <section id="content"></section>', layoutSelector = '#content', rootSelector = '#app';

			// 使用template构造DOM字符串，传入从上一视图读取的变量
			var layoutDomString = kendo.template(layoutDomTemplate)({

			});

			var rt = new kendo.Router({
				routeMissing : function(e) {
					console.error('路由"', e.url, '"不存在，跳转至默认路由 "/" ...');
					e.sender.navigate('/'); // 路由错误
				}
			});
			rt.bind('init', function() {
				layout.render($(rootSelector));
			});

			// 输出至全局变量app
			window.app = $.extend(true, window.app || {}, {
				layout : new kendo.Layout(layoutDomString),
				layoutSelector : layoutSelector,
				router : rt,
				proxy : {
					login : {
						// 对应login.js，发送登录请求，返回签约合同
						type : 'POST',
						url : 'api/rest/cusinf/confirmCustomer', // TODO -
						// 待修改为后端登录URL
						contentType : 'application/json',
					},
					addaccount : {
						// 对应addaccount.js，发送本行账户请求，返回账户信息
						type : 'POST',
						url : 'api/rest/bancar/findMyCard', // TODO -
						// 待修改为后端findMyCard
						// URL
						contentType : 'application/json',
					},
					checkinfo : {
						// 对应checkinfo.js，发送签约账户和互助人信息，返回签约结果
						type : 'POST',
						url : 'api/rest/uancon/contract', // TODO- 待修改为后端登录URL
						contentType : 'application/json',
					}
				}
			});

			// 构造index页面
			var app = window.app, layout = app.layout, router = app.router, layoutSelector = app.layoutSelector;

			// 构造视图DOM模板
			var indexDomTemplate = ' <div class="container-fluid" style="height: 100%;">\
  <h1>中国农业银行掌上银行</h1>\
\
<br/><br/><br/>\
  <div class="add-bar">\
    <button class="btn btn-theme btn-add" data-bind="click: sign"><b>UAN签约</b></button>\
  </div>\
\
</div>';

			// 使用template构造DOM字符串，传入从上一视图读取的变量
			var indexDomString = kendo.template(indexDomTemplate)({

			});

			// index视图view modal
			var indexVM = kendo.observable({
				init : function() {
					// console.log('view init', this.customerName);
				},
				// show: function () {
				// console.log('view show', this.customerName);
				// },
				// hide: function () {
				// console.log('view hide', this.customerName);
				// },
				sign : function() {
					// 跳转路由至 /login
					router.navigate('/login');
				}
			});

			// index视图view，从字符串变量indexDomString中加载DOM结构，绑定ViewModel
			var indexV = new kendo.View(indexDomString, {
				model : indexVM,
				// init: indexVM.init.bind(indexVM),
				// show: indexVM.show.bind(indexVM),
				// hide: indexVM.hide.bind(indexVM),
				sign : indexVM.sign.bind(indexVM),
			});

			// agreement视图路由，渲染指定view
			router.route('/', function() {
				layout.showIn(layoutSelector, indexV);
			});

			// 输出至全局变量app
			window.app = $.extend(true, app, {
				'indexV' : indexV
			});
		}));