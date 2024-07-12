grammar LExpr;

s: e;
e: e op=MULT e #Mult
  | e op=ADD e #Add
 | INT         #int
 ;

 MULT : '*';
 ADD  : '+';
 INT  : [0-9]+;
 WS : [ \t\r\n]+ -> skip ;
