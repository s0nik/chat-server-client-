var app = angular.module("myApp", []);
app.controller("myController", function ($scope, $http, $interval, $rootScope, $filter) {

    var id;
    //retrieving all chats data at the beginning
    $http.get("api/allchats").then(function (response) {
        $scope.chats = response.data.data;
        $rootScope.msgobj = $scope.chats[0]; //object of the first message showing
        $scope.message($rootScope.msgobj); //to display message list of first
        id = $rootScope.msgobj.chatId;
    });

    $interval(function () {
        $scope.allmessage();
    }, 5000);


    $scope.allmessage = function () {
        $http.get("api/allchats").then(function (response) {
            $scope.chats = response.data.data;
            $scope.objarray = $filter('filter')($scope.chats, {'chatId':id}); //gives array with one object
           //converting array into object
            $rootScope.msgobj = $scope.objarray[0];
        });
    };


    //checks for new message every second in object retrived msgobj
    $interval(function () {
        $scope.messages = $rootScope.msgobj.chatMessageList;
    }, 1000);



    // to retrive message of individual  
    $scope.message = function (msg) {
        $scope.messages = msg.chatMessageList;
        $scope.servermsg = {'chatId': msg.chatId};
        $scope.name = msg.name;
        $rootScope.msgobj = msg;
        id = msg.chatId;
    };


    //sending a message
    $scope.send = function () {
        $http.post("api/servermessage", $scope.servermsg).then(function (response) {
            $scope.servermsg.message = ''; //setting message textarea blank after sent
            $scope.allmessage(); //refreshing all data   
        });
    };

});

