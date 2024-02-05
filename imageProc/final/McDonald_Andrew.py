##################################################################################
# Libraries

import cv2
import numpy as np
import matplotlib.pyplot as plt

##################################################################################
# Variables to map the numeric values to the filter names.  We will use later.
PREVIEW   = 0   # Preview Mode
GRAY      = 1   # Grayscale Filter
THRESHOLD = 2   # Threshold Filter
SKIN_DETECTION = 3   # Threshold Filter
GAUSSIAN = 4   # Threshold Filter
EDGE_DETECTION = 5   # Threshold Filter
OBJECT_DETECTION = 6   # Threshold Filter

##################################################################################
# All your filter and tracker functions.
def Threshold_filter(frame):

    frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    
    h = frame.shape[0]
    w = frame.shape[1]

    imgThres = np.zeros((frame.shape[0],frame.shape[1]), dtype=np.uint8)

    for y in range(0,h):
    #Search each pixel in the row
        for x in range(0,w):
        
            #If the pixel value is above 100 set it to white.  If it is below 100 set it to black.
            if (frame[y,x] > 100):
                imgThres[y,x] = 255
            else:
                imgThres[y,x] = 0
    
    
    return imgThres

def Skin_detection(frame):
    min_hsv =[105,40,10]
    max_hsv = [180,175,250]
	# Convert color from bgr to hsv
    frame = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

	# Min and Max HSV values
    min_HSV = np.array(min_hsv, np.uint8)
    max_HSV = np.array(max_hsv, np.uint8)

    skinArea = cv2.inRange(frame, min_HSV, max_HSV)

    # Bitwise And mask
    skinHSV = cv2.bitwise_and(frame, frame, mask=skinArea)

    skinHSV = cv2.cvtColor(skinHSV, cv2.COLOR_HSV2BGR)
    return skinHSV

def Gaussian(frame):
    # Initialization of 5x5 Guassian kernel
    gaussian_filter = np.array([[1,4,7,4,1],[4,16,26,16,4],[7,26,41,26,7],[4,16,26,16,4],[1,4,7,4,1]], dtype=np.float32)/273

    frame = cv2.filter2D(frame, -1, gaussian_filter)
    
    return frame

def Edge_detection(frame):
    frame = Gaussian(frame)
    frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    
    frame = (cv2.Sobel(frame, cv2.CV_64F,1,1,5) * 10).clip(0,255).astype(np.uint8)
    frame = Gaussian(frame)
    ret, frame = cv2.threshold(frame, 80, 255,cv2.THRESH_BINARY)

    return frame
    
def Object_detection(frame):
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    
	# Apply Canny edge detection
    edges = cv2.Canny(gray, 20, 200)
    
	# Find contours
    contours, hierarchy = cv2.findContours(edges, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    
	# Filter out small contours
    min_contour_area = 1000
    contours_large = []
    for cnt in contours:
        area = cv2.contourArea(cnt)
        if area > min_contour_area:
            contours_large.append(cnt)
    
	# Draw bounding box around large contours
    for cnt in contours_large:
        x,y,w,h = cv2.boundingRect(cnt)
        cv2.rectangle(frame, (x,y), (x+w,y+h), (0,255,0), 1)
        
	
    return frame
##################################################################################
# The video image loop.

# Create a VideoCapture object and read from input file
# If the input is taken from the camera, pass 0 instead of the video file name.
cap = cv2.VideoCapture(0)

# Variable to keep track of the current image filter. Default set to Preview.
image_filter = PREVIEW

# Video Loop
while cap.isOpened():
    # Ret = True if frame is read correctly, frame = the frame that was read.
    ret, frame = cap.read()
    # if frame is read correctly ret is True
    if ret:
    
    # Resize the image so your app runs faster.  Less Pixels to Process.  
    # Use Nearest Neighbor Interpolation for the fastest runtime.
        frame = cv2.resize(frame, None, fx = 1, fy = 1, interpolation=cv2.INTER_NEAREST)
    
    # Send your frame to each filter.
        if image_filter == PREVIEW:
        # No filter.
            result = frame
        elif image_filter == THRESHOLD:
        # Send the frame to the Threshold function.
            result = Threshold_filter(frame)
        elif image_filter == GRAY:
        # Convert to grayscale.
            result = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        elif image_filter == SKIN_DETECTION:
		# Detect skin
            result = Skin_detection(frame)
        elif image_filter == GAUSSIAN:
        # Apply guassian filter
            result = Gaussian(frame)
        elif image_filter == EDGE_DETECTION:
		# Detect edges
            result = Edge_detection(frame)
        elif image_filter == OBJECT_DETECTION:
        # Object detection
            result = Object_detection(frame)
        
    
    # Show your frame.  Remember you don't need to convert to RGB.  
        cv2.imshow('frame', result)
    
    # Map all your key codes to your filters/trackers. Capital and lower case.
        key = cv2.waitKey(1)

    # Quit the app.
        if key == ord('Q') or key == ord('q'):
            break
    # Grayscale filter
        elif key == ord('G') or key == ord('g'):
            image_filter = GRAY
    # Threshold filter
        elif key == ord('T') or key == ord('t'):
            image_filter = THRESHOLD
    # Skin Detection filter
        elif key == ord('S') or key == ord('s'):
            image_filter = SKIN_DETECTION
    # Guassian filter
        elif key == ord('U') or key == ord('u'):
            image_filter = GAUSSIAN
    # Edge Detection filter
        elif key == ord('E') or key == ord('e'):
            image_filter = EDGE_DETECTION
    # Object Detection filter
        elif key == ord('O') or key == ord('o'):
            image_filter = OBJECT_DETECTION
    # Preview. No filter.
        elif key == ord('P') or key == ord('p'):
            image_filter = PREVIEW

##################################################################################
# Close the app
cap.release()
cv2.destroyWindow('frame')



