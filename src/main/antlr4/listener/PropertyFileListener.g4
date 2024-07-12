grammar PropertyFileListener;

file : prop+;
prop : ID '=' STRING '\n';
ID   : [a-z]+ ;
STRING : '"' .*? '"';