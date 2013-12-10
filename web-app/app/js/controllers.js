(function() {
    var as = angular.module('myApp.controllers', []);
    as.controller('AppCtrl', function($scope, $rootScope, $http, i18n, $location) {
        $scope.language = function() {
            return i18n.language;
        };
        $scope.setLanguage = function(lang) {
            i18n.setLanguage(lang);
        };
        $scope.activeWhen = function(value) {
            return value ? 'active' : '';
        };

        $scope.path = function() {
            return $location.url();
        };

        $scope.login = function() {
            $scope.$emit('event:loginRequest', $scope.username, $scope.password);
        };

        $scope.logout = function() {
            $rootScope.user = null;
            $scope.username = $scope.password = null;
            $scope.$emit('event:logoutRequest');
        };

        $rootScope.appUrl = "http://localhost:8080/angularjs-grails-sample";

    });

    as.controller('BookListCtrl', function($scope, $rootScope, $http, $location) {
        var load = function() {
            console.log('call load()...');
            $http.get($rootScope.appUrl + '/books.json')
                    .success(function(data, status, headers, config) {
                        $scope.books = data;
                        angular.copy($scope.books, $scope.copy);
                    });
        }

        load();

        $scope.addBook = function() {
            console.log('call addBook');
            $location.path("/new");
        }

        $scope.editBook = function(index) {
            console.log('call editBook');
            $location.path('/edit/' + $scope.books[index].id);
        }

        $scope.delBook = function(index) {
            console.log('call delBook');
            var todel = $scope.books[index];
            $http
                    .delete($rootScope.appUrl + '/books/' + todel.id + '.json')
                    .success(function(data, status, headers, config) {
                        load();
                    }).error(function(data, status, headers, config) {
            });
        }

    });

    as.controller('NewBookCtrl', function($scope, $rootScope, $http, $location) {

        $scope.book = {};

        $scope.saveBook = function() {
            console.log('call saveBook');
            $http
                    .post($rootScope.appUrl + '/books.json', $scope.book)
                    .success(function(data, status, headers, config) {
                        $location.path('/books');
                    }).error(function(data, status, headers, config) {
            });
        }
    });

    as.controller('EditBookCtrl', function($scope, $rootScope, $http, $routeParams, $location) {

        var load = function() {
            console.log('call load()...');
            $http.get($rootScope.appUrl + '/books/' + $routeParams['id'] + '.json')
                    .success(function(data, status, headers, config) {
                        $scope.book = data;
                        angular.copy($scope.book, $scope.copy);
                    });
        }

        load();

        $scope.book = {};

        $scope.updateBook = function() {
            console.log('call updateBook');
            $http
                    .put($rootScope.appUrl + '/books/' + $scope.book.id + '.json',  $scope.book)
                    .success(function(data, status, headers, config) {
                        $location.path('/books');
                    }).error(function(data, status, headers, config) {
            });
        }
    });
}());