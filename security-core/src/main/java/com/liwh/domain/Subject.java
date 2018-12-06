package com.liwh.domain;

/**
 * @author: Liwh
 * @ClassName: Subjet
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-06 12:34 PM
 */
public class Subject {
    private Long id;
    private String mobile;

    Subject(final Long id, final String mobile) {
        this.id = id;
        this.mobile = mobile;
    }

    public static Subject.SubjectBuilder builder() {
        return new Subject.SubjectBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Subject)) {
            return false;
        } else {
            Subject other = (Subject)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$mobile = this.getMobile();
                Object other$mobile = other.getMobile();
                if (this$mobile == null) {
                    if (other$mobile != null) {
                        return false;
                    }
                } else if (!this$mobile.equals(other$mobile)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Subject;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $mobile = this.getMobile();
        result = result * 59 + ($mobile == null ? 43 : $mobile.hashCode());
        return result;
    }

    public String toString() {
        return "Subject(id=" + this.getId() + ", mobile=" + this.getMobile() + ")";
    }

    public static class SubjectBuilder {
        private Long id;
        private String mobile;

        SubjectBuilder() {
        }

        public Subject.SubjectBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public Subject.SubjectBuilder mobile(final String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Subject build() {
            return new Subject(this.id, this.mobile);
        }

        public String toString() {
            return "Subject.SubjectBuilder(id=" + this.id + ", mobile=" + this.mobile + ")";
        }
    }
}

