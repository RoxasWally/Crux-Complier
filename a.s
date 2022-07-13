    .globl main
main:
    enter $(48), $0
    /* nop */
    /* %av0 = addressAt a, null */
    movq a@GOTPCREL(%rip), %r11
    movq %r11, -8(%rbp)
    /* $t1 = 42 */
    movq $42, -16(%rbp)
    /* store $t1, %av0 */
    movq -16(%rbp), %r11
    movq -8(%rbp), %r10
    movq %r11, 0(%r10)
    /* %av1 = addressAt a, null */
    movq a@GOTPCREL(%rip), %r11
    movq %r11, -24(%rbp)
    /* $t2 = load %av1 */
    movq -24(%rbp), %r10
    movq 0(%r10), %r11
    movq %r11, -32(%rbp)
    /* call Symbol(printInt:func(TypeList(int)):void) ($t2) */
    movq -32(%rbp), %rdi
    call printInt
    movq %rax, -40(%rbp)
    leave
    ret
