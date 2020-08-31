HERE_PATH:= $(call my-dir)

LOCAL_PATH := $(HERE_PATH)

LOCAL_PATH := $(HERE_PATH)/../../cacio-sdl/src/main/native
include $(CLEAR_VARS)

LOCAL_SRC_FILES := \
    SDLBlit.c \
    SDLGraphicsEnvironment.c \
    SDLScreen.c \
    SDLSurfaceData.c \
    SDLVolatileSurfaceManager.c
LOCAL_MODULE := cacio-sdl

include $(BUILD_SHARED_LIBRARY)

ifeq ($(TRICK_AIDE_BUILD),1)
    include $(BUILD_EXECUTABLE)
endif
