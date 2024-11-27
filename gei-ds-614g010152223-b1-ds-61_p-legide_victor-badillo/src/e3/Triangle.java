package e3;

public record Triangle(float angle0, float angle1, float angle2) {

    /**
     * Constructs a Triangle object given its three internal angles
     * It is the canonical constructor .
     * @param angle0 Angle 0
     * @param angle1 Angle 1
     * @param angle2 Angle 2
     * @throws IllegalArgumentException if the angles do not sum 180 degrees
     */
    public Triangle(float angle0, float angle1, float angle2){
        if((angle0+angle1+angle2) != 180.0){
            throw new IllegalArgumentException();
        }else{
            this.angle0=angle0;
            this.angle1=angle1;
            this.angle2=angle2;
        }
    }

    /**
     * Copy constructor . Constructs a Triangle using another Triangle .
     * @param t The Triangle object to copy .
     */

    public Triangle( Triangle t) {
        this(t.angle0, t.angle1, t.angle2);
    }

    /**
     * Tests if a triangle is right .
     * A right triangle has one of its angles measuring 90 degrees .
     * @return True if it is right , false otherwise
     */
    public boolean isRight () {
        return angle0 == 90 || angle1 == 90 || angle2 == 90;
    }

    /**
     * Tests if a triangle is acute .
     * A triangle is acute if all angles measure less than 90 degrees .
     * @return True if it is acute , false otherwise
     */
    public boolean isAcute () {
        return angle0<90 && angle1<90 && angle2<90;
    }

    /**
     * Tests if a triangle is obtuse .
     * A triangle is obtuse if it has one angle measuring more than 90 degrees .
     * @return True if it is obtuse , false otherwise
     */
    public boolean isObtuse () {
        return angle0>90 || angle1>90 || angle2>90;
    }

    /**
     * Tests if a triangle is equilateral .
     * A triangle is equilateral if all the angles are the same .
     * @return True if it is equilateral , false otherwise
     */
    public boolean isEquilateral () {
        return angle0==angle1 && angle0==angle2;
    }

    /**
     * Tests if a triangle is isosceles .
     * A triangle is isosceles if it has two angles of the same measure .
     * @return True if it is isosceles , false otherwise
     */
    public boolean isIsosceles () {
        return (angle0==angle1 && angle2!=60) || (angle0==angle2 && angle1!=60) || (angle1==angle2 && angle0!=60);
    }

    /**
     * Tests if a triangle is scalene .
     * A triangle is scalene if it has all angles of different measure .
     * @return True if it is scalene , false otherwise
     */
    public boolean isScalene () {
        return angle0!=angle1 && angle0!=angle2 & angle1!=angle2;
    }

    /**
     * Tests if two triangles are equal .
     * Two triangles are equal if their angles are the same ,
     * regardless of the order .
     * @param o The reference object with which to compare .
     * @return True if they are equal , false otherwise .
     */
    @Override
    public boolean equals ( Object o) {
        if(o==null){
            return false;
        }else if(!(o instanceof Triangle)){
            return false;
        }else{
            if(!((this.angle0 == ((Triangle) o).angle0 && this.angle1 == ((Triangle) o).angle1() && this.angle2 == ((Triangle) o).angle2() ) || ( this.angle0 == ((Triangle) o).angle1() && this.angle1 == ((Triangle) o).angle2 && this.angle2 == ((Triangle) o).angle0() ) || (this.angle0 == ((Triangle) o).angle2() && this.angle1 == ((Triangle) o).angle0 && this.angle2 == ((Triangle) o).angle1))){
                return false;
            }else{
                return true;
            }
        }
    }

    /**
     * Hashcode function whose functioning is consistent with equals .
     * Two triangles have the same hashcode if their angles are the same ,
     * regardless of the order .
     * @return A value that represents the hashcode of the triangle .
     */
    @Override
    public int hashCode () {
        final int prime= 11;
        int result=3;

        //The three angles are equal
        if(this.angle0==this.angle1 && this.angle1==this.angle2){
            result = prime * result + Float.floatToIntBits(this.angle0);
            result= prime * result + Float.floatToIntBits(this.angle1);
            result= prime * result + Float.floatToIntBits(this.angle2);
        }
        // 0 1 2
        else if(this.angle0<this.angle1 && this.angle0<this.angle2 && this.angle1<this.angle2){
            result = prime * result + Float.floatToIntBits(this.angle0);
            result= prime * result + Float.floatToIntBits(this.angle1);
            result= prime * result + Float.floatToIntBits(this.angle2);
        }
        // 0 2 1
        else if(this.angle0<this.angle1 && this.angle0<this.angle2 && this.angle2<=this.angle1){
            result = prime * result + Float.floatToIntBits(this.angle0);
            result= prime * result + Float.floatToIntBits(this.angle2);
            result= prime * result + Float.floatToIntBits(this.angle1);
        }
        // 1 0 2
        else if(this.angle1<this.angle0 && this.angle1<this.angle2 && this.angle0<this.angle2){
            result = prime * result + Float.floatToIntBits(this.angle1);
            result= prime * result + Float.floatToIntBits(this.angle0);
            result= prime * result + Float.floatToIntBits(this.angle2);
        }
        // 1 2 0
        else if(this.angle1<this.angle0 && this.angle1<this.angle2 && this.angle2<=this.angle0){
            result = prime * result + Float.floatToIntBits(this.angle1);
            result= prime * result + Float.floatToIntBits(this.angle2);
            result= prime * result + Float.floatToIntBits(this.angle0);
        }
        // 2 1 0
        else if(this.angle2<this.angle1 && this.angle2<this.angle0 && this.angle1<this.angle0){
            result = prime * result + Float.floatToIntBits(this.angle2);
            result= prime * result + Float.floatToIntBits(this.angle1);
            result= prime * result + Float.floatToIntBits(this.angle0);
        }
        // 2 0 1
        else if(this.angle2<this.angle1 && this.angle2<this.angle0 && this.angle0<=this.angle1){
            result = prime * result + Float.floatToIntBits(this.angle2);
            result= prime * result + Float.floatToIntBits(this.angle0);
            result= prime * result + Float.floatToIntBits(this.angle1);
        }
        return result;
    }
}