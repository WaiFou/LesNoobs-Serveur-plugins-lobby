package fr.lesnoobs.lobby.jump;

public class TimeConverter 
{
       public static String convert(int time)
       {
           @SuppressWarnings("unused")
           int fsec,d,h,m,s,temp=0,i;
           fsec=time;

           if(fsec>=86400)
           {
               temp=fsec/86400;
               d=temp;
               for(i=1;i<=temp;i++)
               {
                   fsec-=86400;
               }
           }
           else
           {
               d=0;
           }
           //For Hours
           if(fsec>=3600)
           {
               temp=fsec/3600;
               h=temp;
               for(i=1;i<=temp;i++)
               {
                   fsec-=3600;
               }            
           }
           else
           {
               h=0;
           }
           //For Minutes
           if(fsec>=60)
           {
               temp=fsec/60;
               m=temp;
               for(i=1;i<=temp;i++)
               {
                   fsec-=60;
               }            
           }
           else
           {
               m=0;
           }
           //For Seconds
           if(fsec>=1)
           {
               s=fsec;            
           }
           else
           {
               s=0;
           }
           
           String temps = "";
           
           if(m==1) {
        	   temps=m+" minute et ";
           }
           else if(m>=2){
        	   temps=m+" minutes et ";
           }
           
           temps+=s+" seconde";
           if(s>1) {
    		   temps+="s";
    	   }
           
           return temps;
       }
}