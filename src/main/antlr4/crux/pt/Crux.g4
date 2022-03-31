grammar Crux;
program
 : declarationList EOF
 ;

declarationList
 : declaration*
 ;


declaration
 : variableDeclaration
| arrayDeclaration
| functionDefinition
 ;

variableDeclaration
 : type Identifier ';'
 ;

type
 : Identifier
 ;

arrayDeclaration
 : type Identifier '[' Integer ']' ';'
 ;

functionDeclaration
 : type Identifier '(' parameterType ')' statements
 ;

literal
 : Integer
 | True
 | False
 ;


SemiColon: ';';

Integer
 : '0'
 | [1-9] [0-9]*
 ;

True: 'true';
False: 'false';

Identifier
 : [a-zA-Z] [a-zA-Z0-9_]*
 ;

WhiteSpaces
 : [ \t\r\n]+ -> skip
 ;

Comment
 : '//' ~[\r\n]* -> skip
 ;
