package xyz.devcham.farmap.farm.dto;

public record RequestCreateFarm(
                String userId,
                String name,
                String location,
                String colorString) {
}
