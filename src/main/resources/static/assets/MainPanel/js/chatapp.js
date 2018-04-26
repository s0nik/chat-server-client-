var app = angular.module("myApp", []);
app.controller("chatController", function ($scope, $http, $interval) {


//    var id = Number('${id!0}');   
    console.log(id);
    if (id == 0) {
        $scope.form = true;
        $scope.chat = false;
    } else {
        $scope.form = false;
        $scope.chat = true;
    }
    if ($scope.chat === true) {
        $http.get("api/message").then(function (response) {
            $scope.chats = response.data.data;
        });
    };


    $scope.submit = function () {
        $http.post("api/chat", $scope.client).then(function () {
            $scope.chat = true;
            $scope.form = false;
        });
    };

    $scope.close = function () {
        $http.get("api/close").then(function (response) {
            $scope.form = true;
            $scope.chat = false;
        });
    };
    $scope.send = function () {
        $http.post("api/message", $scope.msg).then(function (response) {
            $scope.msg.message = '';
            $scope.getdata();
        });
    };

    $scope.getdata = function () {
        if ($scope.chat === true) {
            $http.get("api/message").then(function (response) {
                $scope.chats = response.data.data;
            });
        }
        ;
    };
    
    $interval(function () {
        $scope.getdata();
    }, 5000);

});