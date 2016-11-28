Handlebars.registerHelper('customIf', function(val, options) {
var fnTrue = options.fn, 
    fnFalse = options.inverse;
    
    if(val == "true"){
    	return fnTrue(this);
    }else{
    	return fnFalse(this);
    }
});