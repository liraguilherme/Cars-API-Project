const formulario = document.querySelector("form");
const Iemail = document.querySelector(".email");
const Ipassword = document.querySelector(".password");
const Ibrand = document.querySelector(".brand");
const Imodel = document.querySelector(".model");
const Iyear = document.querySelector(".year");
const Iprice = document.querySelector(".price");
const Imileage = document.querySelector(".mileage");
const Icolor = document.querySelector(".color");
const Ifuel = document.querySelector(".fuel");

function cadastrar() {

//Vai acessar nossa API

fetch("http://localhost:8080/car", 
{ headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  },
  method: "POST",
  body: JSON.stringify({
        email: Iemail.value,
        password: Ipassword.value,
        brand: Ibrand.value,
        model: Imodel.value,
        year:  Iyear.value,
        price: Iprice.value,
        mileage: Imileage.value,
        color: Icolor.value,
        fuel: Ifuel.value
 })
})
.then(function(res){ console.log(res) })
.catch(function(res){ console.log(res) })

};


function limpar(){ //função para limpar os campos
  Iemail.value = "";
  Ipassword.value = "";
  Ibrand.value = "";
  Imodel.value = "";
 Iyear.value = "";
    Iprice.value = "";
     Imileage.value = "";
 Icolor.value = "";
  Ifuel.value  = "";

}


formulario.addEventListener('submit', function(event){
    event.preventDefault();

 
    cadastrar();
    limpar()
})