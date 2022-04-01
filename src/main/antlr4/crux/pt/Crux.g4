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
NOT: '!';
If: 'if';
Else: 'else';
Return: 'return';
Break: 'break';
Continue: 'continue';
For: 'for';

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
assignmentStatement
: designator '=' expression0 ';'
;

assignmentNoSemi
: designator '=' expression0;

callStatement
: callExpression ';'
;

statement
: variableDeclaration
|returnStatement
|callStatement
|assignmentStatement
|assignmentNoSemi
|breakStatement
|ifStatement
|continueStatement
|forStatement
;

ifStatement:
 'if' expression0 statementBlocks ('else' statementBlocks)?
;

forStatement:
'for' '(' assignmentStatement expression0 ';' assignmentNoSemi ')' statementBlocks
;


breakStatement
: 'break' ';'
;
continueStatement
: 'continue' ';'
;
returnStatement
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