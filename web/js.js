/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var navlinks = document.getElementById("navLinks");

function showMenu() {
    navlinks.style.right = "0";
}

function hideMenu() {
    navlinks.style.right = "-200px";
}


var x = document.getElementById("loginForm");
var y = document.getElementById("registerForm");
var z = document.getElementById("btn");

function Register() {
    x.style.left = "-400px";
    y.style.left = "50px";
    z.style.left = "110px";
}
function login() {
    x.style.left = "50px";
    y.style.left = "450px";
    z.style.left = "0";
}

