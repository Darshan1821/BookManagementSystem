var bookApp = angular.module('bookApp',['ngRoute']);

bookApp.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'html/home.html',
	}).when('/home', {
		templateUrl : 'html/home.html',
	}).when('/bookRegistration', {
		templateUrl : 'html/bookRegistration.html',
		controller : 'RegisterBookController'
	}).when('/viewAllBooks', {
		templateUrl : 'html/viewAllBooks.html',
		controller : 'ViewAllBookController'
	}).when('/updateBook/:id', {
		templateUrl : 'html/updateBook.html',
		controller : 'UpdateBookController'
	}).when('/deleteBook/:id', {
		templateUrl : 'html/deleteBook.html',
		controller : 'DeleteBookController'
	}).when('/success/:message', {
		templateUrl : 'html/success.html',
		controller : 'SuccessController'
	}).when('/error/:message', {
		templateUrl : 'html/error.html',
		controller : 'ErrorController'
	}).otherwise({
		redirectTo : '/home'
	})
})


bookApp.controller('ViewAllBookController',function($scope,$http,$location){
	
	$scope.books = [];
	
	$http({
		method : 'post',
		url : 'BookController?action=viewAllBooks',
	}).then(function(result) {
		console.log(result);
		$scope.books = result.data;
	}, function(error) {
		console.log(error);
		$location.path('/error/'+error.data.message);
	})
})

bookApp.controller('RegisterBookController',function($scope,$http,$location){
	$scope.bookName = '';
	$scope.bookAuthor = '';
	$scope.bookCategory = '';
	$scope.bookPages = 0;
	$scope.bookLanguage = '';
	
	$scope.registerBook = function(){
		
		var book = new Object();
		book.bookName = $scope.bookName;
		book.bookAuthor = $scope.bookAuthor;
		book.bookCategory = $scope.bookCategory;
		book.bookPages = $scope.bookPages;
		book.bookLanguage = $scope.bookLanguage;
	
		$http({
			method : 'post',
			url : 'BookController?action=addBook',
			dataType : 'JSON',
			data : JSON.stringify(book),
			contentType : 'application/json',
			mimeType : 'application/json',
		}).then(function(result) {
			$location.path('/success/'+result.data.message);
		}, function(error) {
			$location.path('/error/'+error.data.message);
		})
	}
})

bookApp.controller('UpdateBookController',function($scope,$routeParams,$location,$http){
	$scope.bookId = $routeParams.id;
	$scope.bookName = '';
	$scope.bookAuthor = '';
	$scope.bookCategory = '';
	$scope.bookPages = 0;
	$scope.bookLanguage = '';
	
	var book = new Object();
	book.bookId = $routeParams.id;
	
	$http({
		method : 'post',
		url : 'BookController?action=getBook',
		dataType : 'JSON',
		data : JSON.stringify(book),
		contentType : 'application/json',
		mimeType : 'application/json',
	}).then(function(result) {
		$scope.bookId = result.data.bookId;
		$scope.bookName = result.data.bookName;
		$scope.bookAuthor = result.data.bookAuthor;
		$scope.bookCategory = result.data.bookCategory;
		$scope.bookPages = result.data.bookPages;
		$scope.bookLanguage = result.data.bookLanguage;
	}, function(error) {
		$location.path('/error/'+error.data.message);
	})
	
	$scope.updateBook = function(id){
		var book = new Object();
		book.bookId = id;
		book.bookName = $scope.bookName;
		book.bookAuthor = $scope.bookAuthor;
		book.bookCategory = $scope.bookCategory;
		book.bookPages = $scope.bookPages;
		book.bookLanguage = $scope.bookLanguage;
		
		$http({
			method : 'post',
			url : 'BookController?action=updateBook',
			dataType : 'JSON',
			data : JSON.stringify(book),
			contentType : 'application/json',
			mimeType : 'application/json',
		}).then(function(result) {
			$location.path('/success/'+result.data.message);
		}, function(error) {
			$location.path('/error/'+error.data.message);
		})
	}
})

bookApp.controller('DeleteBookController',function($scope,$http,$location,$routeParams){
	
	$scope.bookId = $routeParams.id;
	$scope.bookName = '';
	$scope.bookAuthor = '';
	$scope.bookCategory = '';
	$scope.bookPages = 0;
	$scope.bookLanguage = '';
	
	var book = new Object();
	book.bookId = $routeParams.id;
	
	$http({
		method : 'post',
		url : 'BookController?action=getBook',
		dataType : 'JSON',
		data : JSON.stringify(book),
		contentType : 'application/json',
		mimeType : 'application/json',
	}).then(function(result) {
		$scope.bookId = result.data.bookId;
		$scope.bookName = result.data.bookName;
		$scope.bookAuthor = result.data.bookAuthor;
		$scope.bookCategory = result.data.bookCategory;
		$scope.bookPages = result.data.bookPages;
		$scope.bookLanguage = result.data.bookLanguage;
	}, function(error) {
		$location.path('/error/'+error.data.message);
	})
	
	$scope.deleteBook = function(id){
		var book = new Object();
		book.bookId = id;
		
		$http({
			method : 'post',
			url : 'BookController?action=deleteBook',
			dataType : 'JSON',
			data : JSON.stringify(book),
			contentType : 'application/json',
			mimeType : 'application/json',
		}).then(function(result) {
			$location.path('/success/'+result.data.message);
		}, function(error) {
			$location.path('/error/'+error.data.message);
		})
	}
})

bookApp.controller('SuccessController',function($scope,$routeParams){
	$scope.message = $routeParams.message;
})

bookApp.controller('ErrorController',function($scope,$routeParams){
	$scope.message = $routeParams.message;
})