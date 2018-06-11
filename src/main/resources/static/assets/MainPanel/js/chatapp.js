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
    }
    ;


    $scope.submit = function () {
        $http.post("api/chat", $scope.client).then(function () {
            $scope.chat = true;
            $scope.form = false;
        });
    };

    $scope.imgUpload = function (files) {
        console.log(files[0]);
        var formData = new FormData();
        formData.append("file", files[0]);
        $http.post("api/uploadimg", formData).then(function (response) {
            console.log(response.data.message);
            $("input#fileSelect").val("");
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


//to make a file upload on select
//app.directive('customOnChange', function () {
//    return {
//        restrict: 'A',
//        link: function (scope, element, attrs) {
//            var onChangeHandler = scope.$eval(attrs.customOnChange);
//            element.on('change', onChangeHandler);
//            element.on('$destroy', function () {
//                element.off();
//            });
//
//        }
//    };
//});


app.directive('ngFiles', ['$parse', function ($parse) {

        function fn_link(scope, element, attrs) {
            var onChange = $parse(attrs.ngFiles);
            element.on('change', function (event) {
                onChange(scope, {$files: event.target.files});
            });
        }
        ;

        return {
            link: fn_link
        }
    }])