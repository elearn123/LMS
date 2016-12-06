function numbercheck(value)
{
	
	if(value!='')
	{
		var IntegerCheck = /^-?\d+$/.test(value);
		if (IntegerCheck==false) 
		return [false,"Please insert integer value "];
		else 
			if(value>24 || value<=0)
			return[false,"Please enter proper value."];
		else 				
			return [true,""];
	}
	else
		return [true,""];

}