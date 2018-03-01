var app = angular.module("myApp", []);
app.controller("myController", function ($scope, $http, $interval, $rootScope) {


    //retrieving all chats data at the beginning
    $http.get("api/allchats").then(function (response) {
        $scope.chats = response.data.data;
        $rootScope.msgobj = $scope.chats[0]; //object of the first message showing
        $scope.message($rootScope.msgobj); //to display message list of first
    });

    $interval(function () {
        $scope.allmessage();
    }, 5000);


    $scope.allmessage = function () {
        $http.get("api/allchats").then(function (response) {
            $scope.chats = response.data.data;
            var a = $rootScope.msgobj.chatId - 1;
            $rootScope.msgobj = $scope.chats[a];
        });
    };



    $interval(function () {
        $scope.refresh($rootScope.msgobj);
    }, 1000);


// to check if there is new message
    $scope.refresh = function (msg) {
        $scope.messages = msg.chatMessageList;
        $rootScope.msgobj = msg;
    };

    // to retrive message of individual  
    $scope.message = function (msg) {
        $scope.messages = msg.chatMessageList;
        $scope.servermsg = {'chatId': msg.chatId};
        $scope.name = msg.name;
        $rootScope.msgobj = msg;
    };


    //sending a message
    $scope.send = function () {
        $http.post("api/servermessage", $scope.servermsg).then(function (response) {
            $scope.servermsg.message = ''; //setting message textarea blank after sent
            $scope.allmessage(); //refreshing all data   
        });
    };

});

