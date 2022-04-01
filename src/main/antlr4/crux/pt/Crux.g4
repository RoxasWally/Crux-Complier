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
 : type Identifier '(' parameterType ')' statementBlocks
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

//Expressions
expression0
: expression1 (comparisons expression1)?
;

expression1
: expression2 operationTwo expression2
;

expression2
: expression3
| operationThree expression3
;

expression3
: '!' expression3
| '(' expression0 ')'
| designator
| callExpression
| literal
;

//statements
assignment
: designator '=' expression0 ';'
;

assignmentNoSemi
: designator '=' expression0;

call
: callExpression ';'
;

statement
: variableDeclaration
|return
|call
|assignment
|assignmentNoSemi
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

 statements
 : statement*
 ;
 statementBlocks
 : '{' statements '}'
 ;
 callExpression
 :
 Identifier '(' expressionList ')'
 ;
 expressionList
 : (expression0 (',' expression0 )*)?
 ;