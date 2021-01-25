package pl.edu.pjwstk.jaz.Request;

public class Photo {

    private String photoName;
    private int photoPosition;

    public Photo() {
    }

    public Photo(String photoName, int photoPosition) {
        this.photoName = photoName;
        this.photoPosition = photoPosition;
    }

    public String getPhotoName() {
        return photoName;
    }

    public int getPhotoPosition() {
        return photoPosition;
    }
}
