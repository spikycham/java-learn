package xyz.devcham.farmap.farm.dto;

public record RequestUpdateFarm(
        String farmId,
        String name,
        String location,
        String colorString) {
}
