package com.geekbrains.cloud.jan.Model;


import lombok.Data;

@Data
public class FileRequest implements CloudMessage {

    private final String fileName;

    @Override
    public CommandType getType() {

        return CommandType.FILE_REQUEST;
    }
}