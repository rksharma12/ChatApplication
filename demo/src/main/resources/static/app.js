
let stompClient=null;


function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    const token=localStorage.getItem('jwt');

    if (!token) {
    console.log("token not found");
    window.location.href = "/index.html";
        return;
    }
    stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket',
    connectHeaders: {
      Authorization: 'Bearer ' +token
    }
});

    stompClient.onConnect = (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/message', (greeting) => {
            showGreeting(JSON.parse(greeting.body).content);
        });
    };

    stompClient.onWebSocketError = (error) => {
        console.error('Error with websocket', error);
    };

    stompClient.onStompError = (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
    };
    stompClient.activate();
}

function disconnect() {
if(stompClient){
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
    }
}

function sendName() {
    stompClient.publish({
        destination: "/app/user",
        body: JSON.stringify({'message': $("#name").val(),
        'reciever':'ritukumarsharma@gmail.com'}
        )
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});

async function login() {
    const email = document.getElementById("loginUser").value;
    const password = document.getElementById("loginPass").value;

    const response = await fetch("/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    });

    const data = await response.json();
    if(data.exc){
        alert(data.error);
        return;
    }
    localStorage.setItem("jwt", data.token);
    window.location.href = "/websocket.html";
}
async function signup() {
    const fullName = document.getElementById("signupUser").value;
    const password = document.getElementById("signupPass").value;
    const email=document.getElementById("email").value;
    const response = await fetch("/auth/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ fullName, password,email })
    });

    const data = await response.json();
    if(data.err){
        alert(data.error);
                return;
    }
    window.location.href = "/index.html";
}
function showSignup() {
    document.getElementById("loginForm").style.display = "none";
    document.getElementById("signupForm").style.display = "block";
}

function showLogin() {
    document.getElementById("signupForm").style.display = "none";
    document.getElementById("loginForm").style.display = "block";
}