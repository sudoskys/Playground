int x = 3;

main()

{

    int i;

    for (i = 1; i <= 2; i++)

        ncre();
}

ncre()

{

    static int x = 1;

    x *= x + 1;

    printf(" %d", x);
}