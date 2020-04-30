var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#stockInfo").html("");
}

function connect() {
    var socket = new SockJS('/stock-tickr');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/stockTickr/price', function (stockPrices) {
            showGreeting(JSON.parse(stockPrices.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/stockTickr", {}, JSON.stringify({'stockSymbol': $("#name").val()}));
    $("#name").val('');
}

function showGreeting(stockQuotes) {
    $("#stockInfo").empty();
    //console.log(stockQuotes)
    for(let stockQuote of stockQuotes){
        //console.log(stockQuote);
        $("#stockInfo").append("<tr><td>" + stockQuote.stockCode + "</td><td>" + stockQuote.stockPrice + "</td></tr>");
    }
    
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();

    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});