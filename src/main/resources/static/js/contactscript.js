document.querySelector("#image").addEventListener('change',(event) =>{
    var file=event.target.files[0];
    var reader=new FileReader();

    reader.onload=()=>{
        document.querySelector("#imagepreview").setAttribute("src",reader.result);
    };

    reader.readAsDataURL(file);


});