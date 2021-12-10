<template> 
 
     <table class="agrupar">
    <td>
      <img
        src="../assets/inventario.png" alt="configuracion" class="imagen"
      />
    </td>
    <td>
        <div class="prueba1">
      <div class="prueba2">
        <p>
          Sistema de gestion de inventarios desarrollado para satisfacer las
          necesidades de control
        </p>
        <p>Control y gestión de productos para todos los clientes</p>
      </div>
      </div>
    </td>
  </table>
 
</template> 
 
<script> 
 
export default { 
    name: "Home", 
 
    data: function(){ 
        return { 
            username: localStorage.getItem('username') || "none" 
        } 
    } 
} 
 
</script> 
 

<style> 
.agrupar {
  display: flex;
  align-items: center;
}
.imagen {

  width: 400px;
  margin-left: 30%;
  margin-top: 30%;
}
.parrafo {

  margin: auto;
  color: aliceblue;
  font-size: 50px;
  text-align: center;
  border: 10px;
  margin-left: 10%;
  margin-right: 10%;
}

.prueba1{
position: absolute;
width: 668px;
height: 402px;
left: 1200px;
top: 220px;

background: rgba(196, 196, 196, 0.15);
border-radius: 65px;
}

.prueba2{
position: absolute;
width: 574px;
height: 252px;
left: 41px;
top: 27px;

font-family: Montserrat;
font-style: italic;
font-weight: bold;
font-size: 30px;
line-height: 34px;
/* or 113% */

text-align: right;
letter-spacing: -2px;
text-transform: uppercase;

color: #FFFFFF;
}
</style> 