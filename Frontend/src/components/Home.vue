<template>
  <table class="contimg">
    <td>
      <img src="../assets/inventario.png" alt="configuracion" class="imagen" />
    </td>
    <td>
      <div class="contParrafo">
        <div class="parrafo">
          <p>
            Sistema de gestion de inventarios desarrollado
            para satisfacer las necesidades de control
          </p>
          <p>Control y gestión de productos <br>
            para todos los clientes</p>
        </div>
      </div>
    </td>
  </table>
</template> 
 
<script>
export default {
  name: "Home",

  data: function () {
    return {
      username: localStorage.getItem("username") || "none",
    };
  },
};
</script> 
 

<style>
.imagen {
  width: 400px;
  margin-left: 30%;
  margin-top: 30%;
}
  .contimg {
    display: flex;
    align-items: center;
    
  }


.contParrafo {
  position: absolute;
  width: 668px;
  height: 402px;
  left: 650px;
  top: 210px;

  background: #c4c4c426;
  border-radius: 65px;
}

.parrafo {
  color: aliceblue;
  
  text-align: center;
  padding:10px;
  border: 10px;
  margin-left: 10%;
  margin-right: 5%;

  font-family: Montserrat;
  font-style: italic;
  font-weight: 570;
  font-size: 30px;
  line-height: 34px;
  /* or 113% */

  text-align: right;
  letter-spacing: -2px;
  text-transform: uppercase;
}
</style> 