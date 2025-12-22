

// features button appearance

let b1btn=document.querySelector("#b1btn");
let b2btn=document.querySelector("#b2btn");
let b3btn=document.querySelector("#b3btn");
let b1=document.querySelector("#box1");
let b2=document.querySelector("#box2");
let b3=document.querySelector("#box3");


b1.addEventListener("mouseover",()=>{
    b1btn.classList.add("flex");
    b1btn.classList.remove("md:hidden");
});
b1.addEventListener("mouseleave",()=>{
    b1btn.classList.remove("flex");
    b1btn.classList.add("md:hidden");
});

b2.addEventListener("mouseover",()=>{
    b2btn.classList.add("flex");
    b2btn.classList.remove("md:hidden");
});
b2.addEventListener("mouseleave",()=>{
    b2btn.classList.remove("flex");
    b2btn.classList.add("md:hidden");
});

b3.addEventListener("mouseover",()=>{
    b3btn.classList.add("flex");
    b3btn.classList.remove("md:hidden");
});
b3.addEventListener("mouseleave",()=>{
    b3btn.classList.remove("flex");
    b3btn.classList.add("md:hidden");
});