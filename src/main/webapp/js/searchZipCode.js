$(function(){

    $("#searchZipcode").on("click",function(){
      jpostal({
        postcode : ["#zipcode"],
        address:{
          "#result" : "%3%4%5"
        }
      
	 
      })

      alert($("#result").val());
      if($("#address").equals("")){
          $("#address").val("#result");
        }else{
          $("#address").val("");
          $("#address").val("#result");
        }

    });
    
  });
