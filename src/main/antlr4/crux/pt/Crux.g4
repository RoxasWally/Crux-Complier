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

functionDefinition
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
AND: '&&';
OR: '||';
NOT: '!=';
If: 'if';
Else: 'else';
Return: 'return';
Break: 'break';
Continue: 'continue';
Loop: 'loop';

Identifier
 : [a-zA-Z] [a-zA-Z0-9_]*
 ;

WhiteSpaces
 : [ \t\r\n]+ -> skip
 ;

Comment
 : '//' ~[\r\n]* -> skip
 ;

parameter
: type Identifier
;

parameterType
: (parameter (',' parameter)*)?
;

GreaterThanEqual: '>=';
LessThanEqual: '<=';
GreaterThan: '>';
LessThan: '<';
Equal: '==';
NotEqual: '!=';
OpenParen: '(' ;
ClosedParen: ')';
OpenBrack: '[';
ClosedBrack: ']';
OpenBrace: '{';
ClosedBrace: '}';
Add: '+' ;
Subtract: '-' ;
Mult: '*';
Div: '/';
Comma: ',';
Assignment: '=';


comparisons
: 'LessThan' | 'GreaterThan' | 'LessThanEqual' | 'GreaterThanEqual' | 'NotEqual' | 'Equal'
;

operationTwo
: 'Add' | 'Subtract' | 'OR';

operationThree
: 'Mult' | 'Div' | 'AND';

statement
: variableDeclaration
|return
|call
|assignment
|break
|if
|continue
|loop
;
break
: 'break' ';'
;
continue
: 'continue' ';'
;
return
: 'return' expression0  ';'
;
designator:
 Identifier ('[' expression0 ']' )?
 ;