package edu.bjtu.example.gym_club;

public class newsLocalItem {
    private int id;
    private String contents;
    private String pictureUri;
    private String fileName;

    public newsLocalItem(int id,String contents,String pictureUri,String fileName){
        this.id = id;
        this.contents = contents;
        this.pictureUri = pictureUri;
        this.fileName = fileName;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
