class Solution {

    private double radius;
    private double xCenter;
    private double yCenter;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.xCenter = x_center;
        this.yCenter = y_center;
    }

    public double[] randPoint() {
        double angle = 2 * Math.PI * Math.random();
        double r = radius * Math.sqrt(Math.random());

        double x = xCenter + r * Math.cos(angle);
        double y = yCenter + r * Math.sin(angle);

        return new double[]{x, y};
    }
}
