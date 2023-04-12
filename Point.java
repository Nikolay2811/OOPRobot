package Robot4;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
 public int getX(){
    return x;
 } 
 public int getY(){
    return y;
 }  

 @Override
 public String toString(){
    return String.format("[%d, %d]", x, y);
 }

 @Override
 public boolean equals(Object obj){ // a == b          a.equals(b)
   if (obj == null || !(obj instanceof Point)){
      return false;
   }

   Point another = (Point) obj; // 5 - float a = 5.0; int b = (int) a;
   return this.x == another.x && this.y== another.y;
  // return Objects.equals(x, another.x) && Objects.equals(y, another.y);
}



}


