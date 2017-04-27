package ru.otus.main;


/**
 *
 */
class Property {

    private Long time;
    private Long count;

    public Property(Long time, Long count) {
        this.time = time;
        this.count = count;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}