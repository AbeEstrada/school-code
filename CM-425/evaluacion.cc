#include <stdio.h>

int main() {
    char proyecto_final;
    char tareas;
    char practicas;
    char exposicion; 
    char examen_final;
    char alumno1[20];
    char alumno2[20];
    char alumno3[20];
    int total1;
    int total2;
    int total3;
    int valor1;
    int valor2;
    int valor3;
    int valor4;
    int valor5;
    
    {
        printf("capturar criterio de evaluacion\n");
        printf("\t PROYECTO FINAL:");
        scanf("%d",&valor1);
    }
    
    {
        printf("\t TAREAS:");
        scanf("%d",&valor2);
    }
    
    {
        printf("\t PRACTICAS:");
        scanf("%d",&valor3);
    }
    
    {
        printf("\t EXPOSICION:");
        scanf("%d",&valor4);
    }
    
    {
        printf("\t EXAMEN FINAL:");
        scanf("%d",&valor5);
    }
    
    {
        printf("\t total= %d\n",(valor1+valor2+valor3+valor4+valor5)); 
    }
    
    printf("CAPTURAR PRIMER ALUMNO \n");
    printf("\t NOMBRE DEL ALUMNO:");
    scanf("%s",alumno1);
    
    {
        printf("\t PROYECTO FINAL:");
        scanf("%d",&valor1);
    }
    
    {
        printf("\t TAREAS:");
        scanf("%d",&valor2);
    }
    
    {
        printf("\t PRACTICAS:");
        scanf("%d",&valor3);
    }
    
    {
        printf("\t EXPOSICION:");
        scanf("%d",&valor4);
    }
    
    {
        printf("\t EXAMEN FINAL:");
        scanf("%d",&valor5);
    }
    
    {
        total1=(valor1+valor2+valor3+valor4+valor5);
        printf("\t total= %d\n",total1); 
    }
    
    printf("CAPTURAR SEGUNDO ALUMNO \n");
    printf("\t NOMBRE DEL ALUMNO:");
    scanf("%s",alumno2);
    
    {
        printf("\t PROYECTO FINAL:");
        scanf("%d",&valor1);
    }
    
    {
        printf("\t TAREAS:");
        scanf("%d",&valor2);
    }
    
    {
        printf("\t PRACTICAS:");
        scanf("%d",&valor3);
    }
    
    {
        printf("\t EXPOSICION:");
        scanf("%d",&valor4);
    }
    
    {
        printf("\t EXAMEN FINAL:");
        scanf("%d",&valor5);
    }
    
    {
        total2=(valor1+valor2+valor3+valor4+valor5); 
        printf("\t total= %d\n",total2);
    }
    
    printf("CAPTURAR TERCER ALUMNO \n");
    printf("\t NOMBRE DEL ALUMNO:");
    scanf("%s",alumno3);
    
    {
        printf("\t PROYECTO FINAL:");
        scanf("%d",&valor1);
    }
    
    {
        printf("\t TAREAS:");
        scanf("%d",&valor2);
    }
    
    {
        printf("\t PRACTICAS:");
        scanf("%d",&valor3);
    }
    
    {
        printf("\t EXPOSICION:");
        scanf("%d",&valor4);
    }
    
    {
        printf("\t EXAMEN FINAL:");
        scanf("%d",&valor5);
    }
    
    {
        total3=(valor1+valor2+valor3+valor4+valor5);
        printf("\t total= %d\n",total3); 
    }
    
    {
        printf("\t PROMEDIO GENERAL=");
        printf("%d\n ",(total1+total2+total3)/3);
    }

    {
        if ((total1>total2)&&(total1>total3)) {
            printf("la calificacion maxima es:");
            printf("%d\n",total1);
            printf("ALUMNO:");
            printf("%s\n",alumno1);
        }
        
        if ((total2>total3)&&(total2>total1)) {
            printf("la calificacion maxima es:");
            printf("%d\n",total2);
            printf("ALUMNO:");
            printf("%s\n",alumno2);
        }
        
        if ((total3>total1)&&(total3>total2)) {
            printf("la calificacion maxima es:");
            printf("%d\n",total3);
            printf("ALUMNO:");
            printf("%s\n",alumno3);
        }
    }
    
    {
        if ((total1<total2)&&(total1<total3)) {
            printf("la calificacion minima es:");
            printf("%d\n",total1);
            printf("ALUMNO:");
            printf("%s\n",alumno1);
        }
        
        if ((total2<total3)&&(total2<total1)) {
            printf("la calificacion minima es:");
            printf("%d\n",total2);
            printf("ALUMNO:");
            printf("%s\n",alumno2);
        }
        
        if ((total3<total1)&&(total3<total2)) {
            printf("la calificacion minima es:");
            printf("%d\n",total3);
            printf("ALUMNO:");
            printf("%s\n",alumno3);
        }
    }
    
    return 0;
}