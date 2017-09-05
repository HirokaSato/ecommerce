$(function(){
    $('#input_form_id_here').autocomplete({
        source: function( req, res ) {
            $.ajax({
                url: "http://example.com/autocomplete/" + encodeURIComponent(req.term),
                dataType: "json",
                success: function( data ) {
                    res(data);
                }
            });
        },
        autoFocus: true,
        delay: 500,
        minLength: 2
    });
  });