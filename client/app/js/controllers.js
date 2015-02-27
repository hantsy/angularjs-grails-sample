(function() {
    var as = angular.module('myApp.controllers', []);
    as.controller('AppCtrl',['$scope', '$rootScope', '$http', 'i18n', '$location', 'apiUrl', function($scope, $rootScope, $http, i18n, $location, apiUrl) {
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


    }]);

    as.controller('BookListCtrl', ['$scope', '$rootScope', '$http', '$location', 'apiUrl', function($scope, $rootScope, $http, $location, apiUrl) {
        var load = function() {
            console.log('call load()...');
            $http.get(apiUrl + '/books.json')
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
                    .delete(apiUrl + '/books/' + todel.id + '.json')
                    .success(function(data, status, headers, config) {
                        load();
                    }).error(function(data, status, headers, config) {
            });
        }

    }]);

    as.controller('NewBookCtrl', ['$scope', '$rootScope', '$http', '$location', 'apiUrl', function($scope, $rootScope, $http, $location, apiUrl) {

        $scope.book = {};

        $scope.saveBook = function() {
            console.log('call saveBook');
            $http
                    .post(apiUrl + '/books.json', $scope.book)
                    .success(function(data, status, headers, config) {
                        $location.path('/books');
                    }).error(function(data, status, headers, config) {
            });
        }
    }]);

    as.controller('EditBookCtrl', ['$scope', '$rootScope', '$http', '$routeParams', '$location', 'apiUrl', function($scope, $rootScope, $http, $routeParams, $location, apiUrl) {

        var load = function() {
            console.log('call load()...');
            $http.get(apiUrl + '/books/' + $routeParams['id'] + '.json')
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
                    .put(apiUrl + '/books/' + $scope.book.id + '.json',  $scope.book)
                    .success(function(data, status, headers, config) {
                        $location.path('/books');
                    }).error(function(data, status, headers, config) {
            });
        }
    }]);
}());