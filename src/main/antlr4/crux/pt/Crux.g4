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
 : type Identifier '(' parameterList ')' statementBlock
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

parameterList
: (parameter ( ',' parameter )* )?
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


op0
: GreaterThanEqual
| LessThanEqual
| NotEqual
| Equal
| GreaterThan
| LessThan
;

op1
: Add
|Subtract
| OR
;

op2
: Mult
| Div
| AND
;

//Expressions
expression0
: expression1 ( op0 expression1 )?
;

expression1
: expression2
| expression1 op1 expression2
;

expression2
: expression3
| expression2 op2 expression3
;

expression3
: '!' expression3
| '(' expression0 ')'
| designator
| callExpression
| literal
;

assignmentStatement
: designator '=' expression0 ';'
;

assignmentStatementNoSemi
: designator '=' expression0
;

callStatement
: callExpression ';'
;

statement
: variableDeclaration
|returnStatement
|callStatement
|assignmentStatement
|breakStatement
|ifStatement
|forStatement
//
//
|assignmentStatementNoSemi
;

ifStatement:
 'if' expression0 statementBlock ('else' statementBlock)?
;

forStatement:
'for' '(' assignmentStatement expression0 ';' assignmentStatementNoSemi ')' statementBlock
;

breakStatement
: 'break' ';'
;

returnStatement
: 'return' expression0  ';'
;
designator:
 Identifier ('[' expression0 ']' )?
 ;

 statementList
 : statement*
 ;
 statementBlock
 : '{' statementList '}'
 ;
 callExpression
 :
 Identifier '(' expressionList ')'
 ;
 expressionList
 : (expression0 ( ',' expression0 )* )?
 ;