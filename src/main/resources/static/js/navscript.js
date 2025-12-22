let sidenav=document.querySelector("#sidenav");
let sidenavbtn=document.querySelector("#sidenavbtn");

sidenavbtn.addEventListener('click',(e)=>{
    e.preventDefault();
    console.log("hello")
    sidenav.classList.toggle("hidden");
//    sidenav.classList.toggle("-translate-x-full");
});

